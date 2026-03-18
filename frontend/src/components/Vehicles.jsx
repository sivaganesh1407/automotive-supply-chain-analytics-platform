import { useState, useEffect } from 'react'

export default function Vehicles({ apiBase }) {
  const [vehicles, setVehicles] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch(`${apiBase}/vehicles`)
      .then(r => r.json())
      .then(setVehicles)
      .catch(() => setError('Could not fetch vehicles'))
      .finally(() => setLoading(false))
  }, [apiBase])

  if (loading) return <div className="text-slate-500">Loading vehicles...</div>
  if (error) return <div className="bg-red-50 text-red-700 p-4 rounded-lg">{error}</div>

  return (
    <div>
      <h2 className="text-2xl font-bold text-slate-800 mb-4">Vehicles</h2>
      <div className="bg-white rounded-xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-slate-100">
            <tr>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">ID</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Model</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Plant</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Production Date</th>
            </tr>
          </thead>
          <tbody>
            {vehicles.length === 0 ? (
              <tr><td colSpan={4} className="px-4 py-8 text-center text-slate-500">No vehicles. Run ETL to load data.</td></tr>
            ) : (
              vehicles.map(v => (
                <tr key={v.id} className="border-t border-slate-100 hover:bg-slate-50">
                  <td className="px-4 py-3">{v.id}</td>
                  <td className="px-4 py-3 font-medium">{v.model}</td>
                  <td className="px-4 py-3">{v.plantLocation}</td>
                  <td className="px-4 py-3">{v.productionDate}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  )
}
